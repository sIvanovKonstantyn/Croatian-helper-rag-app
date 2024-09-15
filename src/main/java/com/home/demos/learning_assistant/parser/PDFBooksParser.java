package com.home.demos.learning_assistant.parser;

import lombok.Cleanup;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PDFBooksParser {

    private static final String CLEAN_UP_PROMPT_TEMPLATE = """
                    Take this text in croatian and fix it.
                    Return only the cleaned up text: %s
            """;

    private final Tesseract tesseract;
    private final ChatClient chatClient;

    @Autowired
    public PDFBooksParser(Tesseract tesseract, ChatClient chatClient) {
        this.tesseract = tesseract;
        this.chatClient = chatClient;
    }

    @SneakyThrows
    public String parse(String path) {
        var parser = new PDFParser(
                new RandomAccessReadBufferedFile(new File(path)));

        @Cleanup var parsed = parser.parse();
        var document = parsed.getDocument();

        var pdfStripper = new PDFTextStripper();
        var pdDocument = new PDDocument(document);

        var text = pdfStripper.getText(pdDocument);

        if (StringUtils.isBlank(text)) {
            text = tesseract.doOCR(new File(path));
        }

        var question = String.format(CLEAN_UP_PROMPT_TEMPLATE, text);

        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
