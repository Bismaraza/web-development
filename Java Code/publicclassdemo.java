// Step 1: Define interface (abstraction for polymorphism)
interface AlertSender {
    void sendAlert(String message, String location);
}

// Step 2: Internet implementation
class InternetAlertSender implements AlertSender {
    @Override
    public void sendAlert(String message, String location) {
        System.out.println("[Internet] SOS sent via Internet");
        System.out.println("Message: " + message + " | Location: " + location);
    }
}

// Step 3: SMS implementation (fallback)
class SMSAlertSender implements AlertSender {
    @Override
    public void sendAlert(String message, String location) {
        System.out.println("[SMS] SOS sent via SMS");
        System.out.println("Message: " + message + " | Location: " + location);
    }
}

// Step 4: Voice recognizer (System verifies voice command)
class VoiceRecognizer {
    boolean verifyVoice(boolean recognized) {
        System.out.println("[System] Verifying voice command...");
        return recognized;
    }
}

// Step 5: GPS service (get user location)
class GPSService {
    String getCurrentLocation(boolean gpsEnabled) {
        if (!gpsEnabled) {
            System.out.println("[System] GPS disabled → using UNKNOWN location");
            return "UNKNOWN";
        }
        return "25.2048N,55.2708E"; // Sample location
    }
}

// Step 6: SOS Service (Main logic for UC2)
class SOSService {
    private final VoiceRecognizer voiceRecognizer = new VoiceRecognizer();
    private final GPSService gpsService = new GPSService();

    public void triggerSOS(boolean usedVoice,
                           boolean voiceRecognized,
                           boolean internetAvailable,
                           boolean smsAvailable,
                           boolean gpsEnabled) {

        // User presses button or uses voice command
        if (usedVoice) {
            System.out.println("[User] Used voice command.");
            if (!voiceRecognizer.verifyVoice(voiceRecognized)) {
                System.out.println("[System] Voice not recognized → ask to repeat.");
                return;
            }
            System.out.println("[System] Voice verified.");
        } else {
            System.out.println("[User] Pressed SOS button.");
        }

        // Trigger SOS
        System.out.println("[System] Triggering SOS process...");
        String location = gpsService.getCurrentLocation(gpsEnabled);
        String message = "Emergency SOS Alert!";

        if (internetAvailable) {
            // Send via Internet
            AlertSender sender = new InternetAlertSender(); // Polymorphism
            sender.sendAlert(message, location);

            System.out.println("[System] Location sharing started (UC3).");
            System.out.println("[System] Confirmation shown to user.");
        } else {
            // Attempt SMS fallback
            System.out.println("[System] No Internet → trying SMS fallback...");
            if (smsAvailable) {
                AlertSender sender = new SMSAlertSender(); // Polymorphism
                sender.sendAlert(message, location);

                System.out.println("[System] Fallback SMS confirmation shown to user.");
            } else {
                System.out.println("[System] Failed to send SOS → Show failure message.");
            }
        }
    }
}

// Step 7: Main class (simulate UC2 scenarios)
public class publicclassdemo {
    public static void main(String[] args) {
        SOSService sos = new SOSService();

        System.out.println("=== Case 1: Voice recognized + Internet available ===");
        sos.triggerSOS(true, true, true, true, true);

        System.out.println("\n=== Case 2: Button pressed + No Internet + SMS available ===");
        sos.triggerSOS(false, false, false, true, true);

        System.out.println("\n=== Case 3: Button pressed + No Internet + No SMS ===");
        sos.triggerSOS(false, false, false, false, true);
    }
}
