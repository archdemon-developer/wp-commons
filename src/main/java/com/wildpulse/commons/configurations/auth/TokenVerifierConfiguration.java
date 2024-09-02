package com.wildpulse.commons.configurations.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.wildpulse.commons.constants.WPCommonConstants;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenVerifierConfiguration {

    @Value(WPCommonConstants.GCP_PROJECT_ID_KEY)
    private String gcpProjectId;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.getApplicationDefault();

        FirebaseOptions firebaseOptions =
                FirebaseOptions.builder()
                        .setCredentials(googleCredentials)
                        .setProjectId(gcpProjectId)
                        .build();

        return FirebaseApp.initializeApp(firebaseOptions);
    }

    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
