package summarizer.command;

import summarizer.receiver.Receiver;

public class TrierAleatoirementFeatureCommand implements Command {
	
	Receiver receiver;
		
	/**
	 * @param receiver
	 * @param ihm
	 */
	public TrierAleatoirementFeatureCommand(Receiver receiver) {
		this.receiver = receiver;
	}		

	@Override
	public void execute() {
		receiver.randomChoose();
	}
	
}
