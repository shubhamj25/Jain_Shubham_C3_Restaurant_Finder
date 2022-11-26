import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;

    private final String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private final List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().compareTo(openingTime) > 0 && getCurrentTime().compareTo(closingTime) < 0;
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());
    }

    public String getName() {
        return name;
    }

    public int getTotalOrderValue(ArrayList<String> order) {
        return 0;
    }
}
