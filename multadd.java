import java.util.*;

public class multadd{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        String[] listOfStrings = userInput.trim().split(" ");


        List<Long> listOflongs = new ArrayList<>();
        for (String s : listOfStrings) {
            listOflongs.add(Long.parseLong(s));
        }
        
        long[] prods = new long[listOflongs.size()];
        for (int i=0; i+1<prods.length;i+=2){
            prods[(int)i]=listOflongs.get((int)i)*listOflongs.get((int)(i+1));
        }
        
        long odd = listOflongs.size()%2;
        
        long[] prods2 = new long[listOflongs.size()];
        prods2[0] = listOflongs.get(0);
        for (int i=1; i+1<prods2.length;i+=2){
            prods2[(int)i]=listOflongs.get((int)i)*listOflongs.get((int)(i+1));
        }
        if(listOflongs.size()>1){
            if(odd==1){
                prods[prods.length-1]=listOflongs.get(listOflongs.size()-1);
            } else{
                prods2[prods2.length-1]=listOflongs.get(listOflongs.size()-1);
            }
            
        }
        
        long mul1 = 0;
        for(int i=0; i<prods.length; i++){
            mul1 += prods[i];
        }
        
        long mul2 = 0;
        for(int i=0; i<prods2.length; i++){
            mul2 += prods2[i];
        }
        
        System.out.println(mul1>mul2 ? mul1 : mul2);
    }
}

