package summarizer.command;

import summarizer.invoker.IHM;
import summarizer.receiver.Receiver;

public class TrierProduitCommand implements Command {

	Receiver receiver;
	IHM ihm;
	
	/**
	 * @param receiver
	 */
	public TrierProduitCommand(Receiver receiver, IHM ihm) {
		this.receiver = receiver;
		this.ihm = ihm;
	}	
	
	@Override
	public void execute() {
		receiver.reduceProduct(ihm.getUserProductsChoice());
	}

}
