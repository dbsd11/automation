package group.bison.automation.common.cmd;

public class EventCommand extends AbstractCommand {

    private String eventId;

    public EventCommand(String nonce, Long timestamp, String eventId) {
        super(nonce, timestamp);
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public static class KillEventCommand extends EventCommand {

        public KillEventCommand(String nonce, Long timestamp, String eventId) {
            super(nonce, timestamp, eventId);
        }
    }

    public static class ReExecEventCommand extends EventCommand {
        private Boolean trueLy;

        public ReExecEventCommand(String nonce, Long timestamp, String eventId) {
            super(nonce, timestamp, eventId);
        }

        public Boolean getTrueLy() {
            return trueLy;
        }

        public void setTrueLy(Boolean trueLy) {
            this.trueLy = trueLy;
        }
    }
}
