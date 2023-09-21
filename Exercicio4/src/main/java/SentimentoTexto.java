import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.core.credential.AzureKeyCredential;

public class SentimentoTexto {
    public static void main(String[] args) {
        TextAnalyticsClient client = new TextAnalyticsClientBuilder()
            .credential(new AzureKeyCredential("5ee524897908480b9a963901c3010129"))
            .endpoint("https://sentimentotexto.cognitiveservices.azure.com/")
            .buildClient();
        
        String inputText = "Eu amo programar em Java!";
        DocumentSentiment documentSentiment = client.analyzeSentiment(inputText);
        System.out.println("O sentimento do texto é: " + documentSentiment.getSentiment());

    }
}
