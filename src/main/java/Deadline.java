public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(by:" + by + ")";
    }
    @Override
    public String getTaskTypeIcon() {
        return "D";
    }
}
