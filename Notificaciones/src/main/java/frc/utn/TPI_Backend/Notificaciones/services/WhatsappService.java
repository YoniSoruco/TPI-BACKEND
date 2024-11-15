package frc.utn.TPI_Backend.Notificaciones.services;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
//    private static final Logger logger = LoggerFactory.getLogger(WhatsappService.class);
//    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
//    private static final String AUTH_TOKEN =System.getenv("TWILIO_AUTH_TOKEN");
//    private static final String TWILIO_WHATSAPP_NUMBER = "whatsapp:+14155238886";
//
//    public WhatsappService() {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//    }
//
//    public void enviarNotificacionWhatsApp(String numeroDestino, String mensaje) {
//        try {
//            if (numeroDestino == null || numeroDestino.isEmpty()) {
//                logger.error("El número de destino no puede ser nulo o estar vacío.");
//                return;
//            }
//
//            Message message = Message.creator(
//                    new PhoneNumber("whatsapp:" + numeroDestino),
//                    new PhoneNumber(TWILIO_WHATSAPP_NUMBER),
//                    mensaje
//            ).create();
//
//            logger.info("Mensaje enviado: {}", message.getSid());
//        } catch (ApiException e) {
//            logger.error("Error al enviar el mensaje de WhatsApp: {}", e.getMessage(), e);
//        }
//   }
}