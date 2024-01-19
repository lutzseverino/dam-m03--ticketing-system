package cat.lasalle.commons.ticket;

import cat.lasalle.commons.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class Ticket {
    private final long id;
    private final Date creationDate = new Date();
    private final String subject;
    private final Set<User> assignees = new HashSet<>();
    private final Set<User> collaborators = new HashSet<>();
    private final List<Message> messages = new ArrayList<>();
    @Setter
    private String description;
    @Setter
    private Status status;
    @Setter
    private Priority priority;
    @Setter
    private Date lastUpdated;
    @Setter
    private User requester;

    public Ticket(long id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    @Getter
    public enum Priority {
        LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);

        private final int value;

        Priority(int value) {
            this.value = value;
        }
    }

    public enum Status {
        OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }

    @Getter
    public static class Message {
        private final long messageId;
        private final List<Message> replies = new ArrayList<>();
        @Setter
        private User sender;
        @Setter
        private Date sentDate;
        @Setter
        private String body;

        public Message(long messageId) {
            this.messageId = messageId;
        }
    }
}
