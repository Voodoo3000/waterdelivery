package kz.epam.waterdelivery.command;

public class CommandResult {
    private String view;
    private boolean redirection;

    public CommandResult() {
    }
    public CommandResult(String view) {
        this.view = view;
    }

    public CommandResult(String view, boolean redirection) {
        this.view = view;
        this.redirection = redirection;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirection() {
        return redirection;
    }

    public void setRedirection(boolean redirection) {
        this.redirection = redirection;
    }
}
