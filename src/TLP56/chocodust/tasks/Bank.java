package TLP56.chocodust.tasks;

import TLP56.chocodust.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(ItemIds.CHOCOLATEBAR).count() == 0;
    }

    @Override
    public void execute() {

        ctx.bank.open();

        if(ctx.inventory.select().id(ItemIds.CHOCOLATEDUST).count() > 0) {
            ctx.bank.deposit(ItemIds.CHOCOLATEDUST, 28);
        }

        if(ctx.bank.select().id(ItemIds.CHOCOLATEBAR).count() == 0){
            ctx.controller().stop();
            ctx.controller.stop();
        }
        ctx.bank.withdraw(ItemIds.CHOCOLATEBAR, 28);

        ctx.bank.close();

    }
}
