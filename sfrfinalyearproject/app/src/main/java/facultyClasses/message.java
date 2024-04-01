package facultyClasses;

public class message {


        private String firstName;
        private String lastName;
        private String dateTime;
        private int imageResource; // Add image resource ID field

        public message(String firstName, String lastName, String dateTime, int imageResource) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateTime = dateTime;
            this.imageResource = imageResource;
        }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    // Getters and setters
        // You can generate these automatically in Android Studio by right-clicking and selecting "Generate" > "Getter and Setter"
    }








