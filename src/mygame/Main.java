package mygame;


/**
 * test
 * @author normenhansen
 */
public class Main  {
    private Render rndr;

    public static void main(String[] args) {
        if(args.length == 3){
            if (args[0].equals("-a")&&(args[1].equals("-p"))){
                Main app = new Main(true, args[2]);
            } 
        }else{
        Main app = new Main(false, null);
        }
        
    }
    public Main(boolean b , String path){
           rndr = new Render(b , path);
           
        
    }
    



}
