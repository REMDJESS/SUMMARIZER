package summarizer.command;

import summarizer.invoker.IHM;
import summarizer.receiver.Receiver;

public class TrierFeatureCommand implements Command {
	
	Receiver receiver;
	IHM ihm;
	
	/**
	 * @param receiver
	 * @param ihm
	 */
	public TrierFeatureCommand(Receiver receiver, IHM ihm) {
		this.receiver = receiver;
		this.ihm = ihm;
	}		

	@Override
	public void execute() {
		receiver.reduceFeature(ihm.getUserListFeature());
	}
	
}
