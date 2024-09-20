package org.example.productpage2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea termsAndConditions;
    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        termsAndConditions.setText("Welcome to SimpleStock, a home inventory management application. By using SimpleStock (\"the App\"), you agree to comply with and be bound by the following terms of use. Please read these terms carefully. If you do not agree to these terms, you should not use the App.\n\n" +
                        "1. Acceptance of Terms\n" +
                        "By accessing or using SimpleStock, you agree to be bound by these Terms of Use and any other policies or guidelines that we may publish from time to time. These Terms of Use apply to all users of the App.\n\n" +
                        "2. Description of the App\n" +
                        "SimpleStock is designed to help users manage and organize their home inventory. The App allows you to track items, their quantities, descriptions, and other related information.\n\n" +
                        "3. User Responsibilities\n" +
                        "As a user of the App, you agree to:\n" +
                        "- Provide accurate, complete, and up-to-date information when using the App.\n" +
                        "- Use the App only for personal, non-commercial purposes.\n" +
                        "- Ensure the security of your account credentials (if applicable) and notify us immediately if you suspect any unauthorized use.\n" +
                        "- Refrain from using the App for any illegal or unauthorized purpose, or in a way that could harm other users or the App's functionality.\n\n" +
                        "4. Privacy Policy\n" +
                        "Your use of the App is also governed by our Privacy Policy, which explains how we collect, use, and protect your personal data. By using the App, you agree to the terms of our Privacy Policy.\n\n" +
                        "5. Intellectual Property\n" +
                        "All content, features, and functionality of the App, including but not limited to text, graphics, logos, and software, are the intellectual property of SimpleStock and are protected by applicable laws. You may not copy, modify, distribute, or otherwise exploit the content without our prior written consent.\n\n" +
                        "6. User-Generated Content\n" +
                        "If you upload or provide any content within the App (such as item descriptions or images), you retain ownership of that content. However, you grant SimpleStock a non-exclusive, royalty-free, worldwide license to use, modify, and display the content as necessary to provide the services of the App.\n\n" +
                        "7. Termination\n" +
                        "We reserve the right to suspend or terminate your access to the App at any time, without notice, for conduct that we believe violates these Terms of Use or is harmful to other users, us, or third parties, or for any other reason.\n\n" +
                        "8. Limitations of Liability\n" +
                        "The App is provided \"as is\" and \"as available\" without warranties of any kind, either express or implied. We do not warrant that the App will be uninterrupted or error-free. To the fullest extent permitted by law, SimpleStock will not be liable for any damages arising from your use or inability to use the App, including but not limited to direct, indirect, incidental, or consequential damages.\n\n" +
                        "9. Modifications to Terms\n" +
                        "We reserve the right to modify these Terms of Use at any time. Any changes will be effective immediately upon posting the updated Terms of Use. Your continued use of the App after such changes constitutes your acceptance of the new terms.\n\n" +
                        "10. Governing Law\n" +
                        "These Terms of Use are governed by and construed in accordance with the laws of [Your Country/State], without regard to its conflict of law principles.\n\n" +
                        "11. Contact Information\n" +
                        "If you have any questions or concerns regarding these Terms of Use, please contact us at [SimpleStock.com].");
    }

    @FXML
    protected void onAgreeCheckBoxClick(){
        boolean accepted = agreeCheckBox.isSelected();
        nextButton.setDisable(!accepted);
    }
    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage)nextButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


}