package cs544.exercise13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Aspect
public class TraceAdvice {
    @After("execution(* cs544.exercise13_1.EmailSender.sendEmail(..))  && args(email, message)")
    public void traceaftermethod(JoinPoint joinpoint, String email, String message) {
        System.out.print(LocalDateTime.now() +" method= "+joinpoint.getSignature().getName());
        System.out.println(" address= " +email + " message= " +message);
        System.out.println("outgoing mail server = "+((EmailSender)joinpoint.getTarget()).getOutgoingMailServer());
    }
}
