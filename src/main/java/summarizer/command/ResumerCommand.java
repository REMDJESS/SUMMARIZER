package summarizer.command;

import java.io.IOException;

import summarizer.receiver.Receiver;

public class ResumerCommand implements Command {

	Receiver receiver;
	
	/**
	 * @param receiver
	 */
	public ResumerCommand(Receiver receiver) {
		this.receiver = receiver;
	}		
	
	@Override
	public void execute() {
		try {
			receiver.jsonExport();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
