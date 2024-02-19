package cat.lasalle.commons.ticket;

import lombok.Getter;

@Getter
public enum PriorityDTO {
    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);

    private final int value;

    PriorityDTO(int value) {
        this.value = value;
    }
}
