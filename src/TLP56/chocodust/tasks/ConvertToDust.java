package TLP56.chocodust.tasks;

import TLP56.chocodust.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class ConvertToDust extends Task {

    public ConvertToDust(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        int b = 0;

        for(Item item : ctx.inventory.select()){
            if(item.id() == ItemIds.KNIFE){
                b++;
                break;
            }
        }

        for(Item item : ctx.inventory.select()) {
            if(item.id() == ItemIds.CHOCOLATEBAR){
                b++;
                break;
            }

        }

        return b == 2;
    }

    @Override
    public void execute() {

        final int currentChocobars = ctx.inventory.select().count();

        Item i = ctx.inventory.select().id(ItemIds.KNIFE).poll();
        i.interact("Use");

        ctx.inventory.select().id(ItemIds.CHOCOLATEBAR).poll().click();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(ItemIds.CHOCOLATEBAR).count() == 0;
            }
        }, 1400, 28);

    }
}
