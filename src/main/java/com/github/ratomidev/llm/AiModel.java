package com.github.ratomidev.llm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AiModel {

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String GROQ_API_KEY = "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"; // Replace with your actual Groq API key

    public String getAnswer(String question) {
        try {
            // Create the URL object
            URL url = new URL(GROQ_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + GROQ_API_KEY);

            // Enable input and output streams
            connection.setDoOutput(true);

            // Create the JSON payload
            String jsonInputString = String.format("{\n" +
                    "\"model\": \"llama-3.3-70b-versatile\",\n" +
                    "\"messages\": [{\n" +
                    "    \"role\": \"user\",\n" +
                    "    \"content\": \"%s\"\n" +
                    "}]\n" +
                    "}", question);

            // Write the JSON payload to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response to extract the "content" field
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.toString());
                String content = rootNode
                        .path("choices")
                        .get(0)
                        .path("message")
                        .path("content")
                        .asText();

                // Return the content
                return content;
            } else {
                // Handle error response
                return "Error: " + responseCode ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        AiModel client = new AiModel();
        String answer = client.getAnswer("Explain the importance of fast language models");
        System.out.println(answer);
    }
}