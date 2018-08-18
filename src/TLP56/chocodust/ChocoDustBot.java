package TLP56.chocodust;

import TLP56.chocodust.tasks.Bank;
import TLP56.chocodust.tasks.ConvertToDust;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="ChocoDust Bot", description =  "A bot for turning chocolate bars into chocolate dust.", properties = "client=4;author=TLP56;topic=1347107")
public class ChocoDustBot extends PollingScript<ClientContext> {

    List<Task> todoList = new ArrayList<Task>();

    @Override
    public void start() {

        todoList.add(new Bank(ctx));
        todoList.add(new ConvertToDust(ctx));

    }

    @Override
    public void poll() {

        for (Task t : todoList) {
            if (t.activate()) {
                t.execute();
                break;
            }
        }

    }
}