package cmd;
@FunctionalInterface
public interface Command {
	public void exec(double dt);
}
