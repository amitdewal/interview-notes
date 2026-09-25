// Eager Initialization

// The object is created when the class is loaded.
// Class loading
//      ↓
// new Singleton()
//      ↓
// instance created

/* 
class  Singleton{
private static Singleton instance = new Singleton();

private Singleton(){

}

public static Singleton getInstance(){
    return instance;
}
}
*/

// Lazy Initialization
/* 
class Singleton {
    private static Singleton instance; // null

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
*/

// Problem
// This implementation is not thread-safe.

// Suppose two threads arrive simultaneously:

// Thread A                    Thread B

// instance == null            instance == null
//       ↓                           ↓
// new Singleton()              new Singleton()
//       ↓                           ↓
//     Object 1                    Object 2

// Synchronized Method
/*
class Singleton {
    private static Singleton instance; // null

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
*/
// Thread A
//    ↓
// acquire lock
//    ↓
// instance == null
//    ↓
// create object
//    ↓
// release lock

// Thread B
//    ↓
// acquire lock
//    ↓
// instance != null
//    ↓
// return same object

// Double-Checked Locking

// Why two checks?
// Thread A                  Thread B

// instance == null          instance == null
//       ↓                         ↓
//  acquire lock              waits
//       ↓
//  instance == null
//       ↓
//  create object
//       ↓
//  release lock
//                             acquire lock
//                                 ↓
//                          instance != null
//                                 ↓
//                          don't create object

class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {

            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}

public class Main {

}
