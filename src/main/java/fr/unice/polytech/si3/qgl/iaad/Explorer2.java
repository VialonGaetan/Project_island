package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.engine.Engine;
import fr.unice.polytech.si3.qgl.iaad.engine.StandardEngine;
import fr.unice.polytech.si3.qgl.iaad.format.Format;
import fr.unice.polytech.si3.qgl.iaad.format.json.JsonFormat;


public class Explorer2 implements IExplorerRaid
{
    private Format format;
    private Engine engine;

    @Override
    public void initialize(String string)
    {
        format = new JsonFormat();
        engine = new StandardEngine();
        engine.setContext(format.stringToContext(string));
    }

    @Override
    public String takeDecision()
    {
        return format.decisionToString(engine.takeDecision());
    }

    @Override
    public void acknowledgeResults(String s)
    {
        engine.acknowledgeResults(format.stringToResult(s));
    }

    @Override
    public String deliverFinalReport()
    {
        return engine.deliverFinalReport();
    }
}
