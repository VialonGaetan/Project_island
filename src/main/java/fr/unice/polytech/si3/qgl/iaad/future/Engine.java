package fr.unice.polytech.si3.qgl.iaad.future;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Engine
{
    void setContext(Context context);

    Decision takeDecision();

    void acknowledgeResults(Result result);

    String deliverFinalReport();
}
