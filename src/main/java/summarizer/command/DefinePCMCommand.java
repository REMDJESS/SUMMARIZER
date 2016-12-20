package summarizer.command;

import java.io.IOException;

import summarizer.invoker.IHM;
import summarizer.receiver.Receiver;

public class DefinePCMCommand implements Command {

	Receiver receiver;
	IHM ihm;
	
	/**
	 * @param receiver
	 * @param ihm
	 */
	public DefinePCMCommand(Receiver receiver, IHM ihm) {
		this.receiver = receiver;
		this.ihm = ihm;
	}		
	
	@Override
	public void execute() {
		try {
			receiver.setPCM(ihm.getPCMFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
