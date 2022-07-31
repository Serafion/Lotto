package pl.lotto.numberreceiver.datastorage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


class NumberLoader implements Loadable{
    @Override
    public HashMap<LocalDateTime,List <UserInput>> loadDatabase(){
        try(ObjectInputStream numbersDatabase = new ObjectInputStream(new BufferedInputStream(new FileInputStream("userinput.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    return (HashMap<LocalDateTime, List<UserInput>>) numbersDatabase.readObject();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch(InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch(IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
        return new HashMap<>();
    }

}
