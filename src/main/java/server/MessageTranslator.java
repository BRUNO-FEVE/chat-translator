package server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MessageTranslator {

    private String language;

    public MessageTranslator(String language) {
        this.language = language;
    }

    public String translate(String message) throws IOException {
        var OPENAI_API_KEY = "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";

        var BODY_REQUEST = String.format("""
            {
                "model": "gpt-3.5-turbo",
                "messages": [
                    {
                        "role": "system",
                        "content": "You are a translator assistant, skilled with the most languages knowledge."
                    },
                    {
                        "role": "user",
                        "content": "Translate the text to the %s language. Text: %s"
                    }
                ]
            }
            """, this.language, message);

        HttpClient apacheClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);

        StringEntity entity = new StringEntity(BODY_REQUEST);
        post.setEntity(entity);

        HttpResponse response = apacheClient.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());

        String key = "\"content\":";
        int contentIndex = responseBody.indexOf(key);

        if (contentIndex != -1) {
            int start = responseBody.indexOf("\"", contentIndex + key.length()) + 1;
            int end = responseBody.indexOf("\"", start);

            String content = responseBody.substring(start, end);

            return content;
        }
        return "Error on Translating";

    }
}
