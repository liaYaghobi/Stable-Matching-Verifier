/*Lia Yaghobi
  Project 1: Stable Matching */

import java.io.File;
import java.util.Scanner;

public class Project1{

File file = new File("input.txt");
int N;
int[][] pref;
int[][] pairs;
   
public Scanner openFile(){

  try{
    Scanner sc = new Scanner(file);
    System.out.println("File Succesfully Opened");

    N = sc.nextInt();     
    pref = new int[2*N][N];   
    pairs = new int[N][2];

    return sc;  
    }

  catch(Exception e){ 
    System.out.println("Erorr opening file");
  }
return null; 
}

public void populateArrays(Scanner sc){

sc.nextLine();

for (int i = 0; i < 2*N; i++){  
  for(int j = 0; j < N; j++){
     pref[i][j] = sc.nextInt();
    }
  }
    sc.nextLine();

for(int k = 0; k < N; k++){
  for(int p = 0; p < 2; p++){
    pairs[k][p] = sc.nextInt();
    }
  }  

}

public void solve(){

  int resetFields; 
  int swapCount = 0;
  int row = 0;
  int key = 0;
  
while(row < N && swapCount < 100){
 resetFields = 0;
    for(int j = 0; j < N; j++){              
      if(pref[row][j] == pairs[row][1]) {         //gets man's prefered women
        
        for(int mensIndex = 0; mensIndex<j; mensIndex++){
         int man = row + 1;                   
         int women = pref[row][mensIndex];
          
          for(int x = 0; x < N; x++){              //gets man's preffered woman's husband
          if(women == pairs[x][1])
              key = x;  }

              for(int k = 0; k <N; k++){
                if(pref[(women-1)+N][k] == pairs[key][0] ){   //checks that womans preferences 
                  for(int index = 0; index<k; index++){
      
                        int womensPref = pref[(women-1)+N][index];
                       
                        if(womensPref == man){         //if that womans preference is 'man', swap
                          swapWomen(pairs,row,key);
                          swapCount += 1;
                          resetFields = 1;
                         }
                      }
                    }
                } 
            }             
        }
    } /*for and if loops end */

 if(resetFields == 1){row = 0;}  /*if swap was done, reset row to 0 */
 else{row++;}                        

  }   /*whileloop ends */

  System.out.println(swapCount);

  for(int k = 0; k < N; k++){
  System.out.println(pairs[k][0]+ " " + pairs[k][1]);}

}

public void swapWomen(int[][] arr, int a, int b) 
{
  int temp = arr[a][1];     /* col 1 represents women */
  arr[a][1] = arr[b][1];
  arr[b][1] = temp;

}



public static void main(String[] args){

    Project1 s = new Project1();
    s.populateArrays(s.openFile());
    s.solve();
  }
}
