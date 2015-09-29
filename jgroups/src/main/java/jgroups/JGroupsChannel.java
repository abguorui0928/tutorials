package jgroups;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class JGroupsChannel extends ReceiverAdapter {

	private JChannel channel;

	public void start() throws Exception {
		channel = new JChannel();
		channel.setReceiver(this);
		// channel.setDiscardOwnMessages(true);
		channel.connect("test");
		eventLoop();
		channel.close();
	}

	private void eventLoop() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.print("> ");
				String line = in.readLine().toLowerCase();
				if (line.startsWith("quit") || line.startsWith("exit")) {
					break;
				}
				channel.send(new Message(null, line));
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void receive(Message msg) {
		System.out.println(msg.getSrc() + ": " + msg.getObject());
	}

	@Override
	public void viewAccepted(View view) {
		System.out.println("** view: " + view);
	}

	public static void main(String[] args) throws Exception {
		JGroupsChannel jGroupsChannel = new JGroupsChannel();
		jGroupsChannel.start();
	}
}
