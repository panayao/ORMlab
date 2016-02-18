package com.cs407_android.ormlab;

/**
 * Created by njaunich on 2/18/16.
 */
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
/**
 * Generates entities and DAOs for the example project DaoExample.
 *
 * Run it as a Java application (not Android).
 *
 * @author Markus
 */
public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(3, "com.cs407_android.ormlab"); //Scheme for GreenDAO ORM
        createDB(schema);
        new DaoGenerator().generateAll(schema, "../DaoExampleGenerator/src-gen");
        //where you want to store the generated classes.
    }

    private static void createDB(Schema schema) {

        //Add Guest
        Entity guest = schema.addEntity("Guest");
        guest.addIdProperty();
        guest.addStringProperty("firstName");
        guest.addStringProperty("lastName");
        guest.addStringProperty("email");
        guest.addStringProperty("phone");

        //Order table
        Entity guestList = schema.addEntity("GuestList");
        guestList.setTableName("GuestList");
        guestList.addIdProperty();

        Property addDate = guestList.addDateProperty("date").getProperty();
        Property guestId = guestList.addLongProperty("guestId").notNull().getProperty();

        //Add one-to-one relationship between guest and guestId
        guestList.addToOne(guest, guestId);

        //Add one-to-many relationship between guest and guestList
        ToMany guestToGuestList = guestList.addToMany(guest, guestId);

        //Set name of Entity
        guestToGuestList.setName("GuestList");

        //Set ordering of entries within Entity
        guestToGuestList.orderAsc(addDate);

    }

}