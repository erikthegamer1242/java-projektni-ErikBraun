package entity;

public record Stop (
        Integer id,
        String location
) {
    public void printStop() {
        System.out.println("Stop Location: " + location);
    }
}

