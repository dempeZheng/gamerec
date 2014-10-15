package cn.mob.gamerec.queue.server;


public interface Action {

    public byte[] execute(ActionContext context, byte[] message);

}
