package sudoku;

import java.time.Clock;
import javax.swing.JOptionPane;

public class Sudoku
{
    private static boolean FindEmpty(int[][] grid,int[] a,int m,int n)
    {
        int row,col;
        for (row=0;row<m;row++)
            for(col=0;col<n;col++)
                if(grid[row][col]==0)
                {
                    a[0]=row;
                    a[1]=col;
                    return true;
                }
        return false;
    }
    private static boolean CheckRow(int[][] grid,int row,int c,int num,int n,int x)
    {
        for (int col=0;col<n;col++)
            if(x==0 || (x==1 && col!=c))
                if(grid[row][col]==num)
                    return true;
        return false;
    }
    private static boolean CheckCol(int[][] grid,int r,int col,int num,int m,int x)
    {
        for (int row=0;row<m;row++)
            if(x==0 || (x==1 && row!=r))
                if(grid[row][col]==num)
                    return true;
        return false;
    }
    private static boolean CheckOne(int[][] grid,int r,int c,int boxStartRow,int boxStartCol,int num,int x)
    {
        for(int row=0;row<3;row++)
            for (int col=0;col<3;col++)
                if(x==0 || (x==1 && col+boxStartCol!=c && row+boxStartRow!=r))
                    if(grid[row+boxStartRow][col+boxStartCol]==num)
                        return true;
        return false;
    }
    private static boolean Check(int[][] grid,int row,int col,int num,int m,int n,int x)
    {
        if(!CheckRow(grid,row,col,num,n,x))
            if(!CheckCol(grid,row,col,num,m,x))
                if(!CheckOne(grid,row,col,row-row%3,col-col%3,num,x))
                    return true;
        return false;
    }
    private static boolean Test(int[][] grid,int m,int n)
    {
        for(int row=0;row<m;row++)
            for (int col=0;col<n;col++)
                if(grid[row][col]!=0)
                    if(!Check(grid,row,col,grid[row][col],m,n,1))
                    {
                        //System.out.println(""+row+" "+col+" "+grid[row][col]);
                        return false;
                    }
        return true;
    }
    public static boolean Solve(int[][] grid,int m,int n)
    {
        int[] a=new int[2];
        int r,c;
        if (!FindEmpty(grid,a,m,n))
            return true;
        r=a[0];
        c=a[1];
        for (int num = 1; num <= 9; num++)
        {
            if(Check(grid,r,c,num,m,n,0))
            {
                grid[r][c] = num;
                if(Solve(grid,m,n))
                    return true;
                grid[r][c]=0;
            }
        }
        return false;
    }       
    private static void print(int[][] grid,int m,int n)
    {
        for (int row = 0; row < m; row++)
        {
            for (int col = 0; col < n; col++)
                System.out.print(grid[row][col]+" ");
            System.out.println("");
        }
    }
    public static void ready(int[][] grid)
    {
        int m=9,n=9;
        //print(grid,m,n);
        //System.out.println("");
        if(Test(grid,m,n))
        {
            //System.out.println("Good");
            //JOptionPane.showMessageDialog(null,"Good");
            if(Solve(grid,m,n) == true)
            {
                //System.out.println("Solution exists");
                JOptionPane.showMessageDialog(null,"Solution exists");
                //print(grid,m,n);
            }
            else
                //System.out.println("No solution exists");
                JOptionPane.showMessageDialog(null,"No solution exists");
        }
        else
            //System.out.println("Bad");
            JOptionPane.showMessageDialog(null,"Bad");
    }
    public static int ready2(int[][] grid)
    {
        int m=9,n=9,f=0;
        
        //print(grid,m,n);
        //System.out.println("");
        if(Test(grid,m,n))
        {
            //System.out.println("Good");
            //JOptionPane.showMessageDialog(null,"Good");
            if(Solve(grid,m,n) == true)
            {
                //System.out.println("Solution exists");
                //JOptionPane.showMessageDialog(null,"Solution exists");
                //print(grid,m,n);
                f=1;
            }
            else
                //System.out.println("No solution exists");
                JOptionPane.showMessageDialog(null,"No solution exists");
        }
        else
            //System.out.println("Bad");
            JOptionPane.showMessageDialog(null,"Bad");
        return f;
    }
    public static void main(String[] args)
    {    
        /*int[][] grid = new int[][]{{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};
                            /*
                                3 1 6 5 7 8 4 9 2
                                5 2 9 1 3 4 7 6 8
                                4 8 7 6 2 9 5 3 1
                                2 6 3 4 1 5 9 8 7
                                9 7 4 8 6 3 1 2 5
                                8 5 1 7 9 2 6 4 3
                                1 3 8 9 4 7 2 5 6
                                6 9 2 3 5 1 8 7 4
                                7 4 5 2 8 6 3 1 9
                            */
        /*int[][] grid = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        //ready(grid);
        //int c=Integer.parseInt("a");
        //System.out.println(c);
    }
}
