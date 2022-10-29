package immutable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * class : SparseMatricesOperation
 * 
 * @author Abhinav
 * @since 26 Oct 2022 09:25 A.M.
 */


public class SparseMatrix{
	private final int[][] sparseMatrix;
	 int size=0;
	 int len=0;
	 int row=0,col=0;
    @SuppressWarnings("unused")
	private SparseMatrix(int[][] a){
        sparseMatrix = Arrays.copyOf(a, a.length);
    }
    
    public  SparseMatrix() {
		this.sparseMatrix = null;		
	}
    
    /**
	 * Desc= We calculate for the zeros and non zero elements: 
	 * 
	 * @param array
	 * @param rows
	 * @param column
	 * @return int number of non zero's.
	 * @throws AssertionError
	 */
    public int calculateSize(int[][] a,int rows,int column) {
    	 int size = 0;
    	 int zeroSize=0;
    	 this.row=rows;
    	 this.col=column;
         for (int i = 0; i < rows; i++)
         {
             for (int j = 0; j < column; j++)
             {
                 if (a[i][j] != 0)
                 {
                     size++;
                 }
                 else {
					zeroSize++;
				}
             }
         }
         if(zeroSize<=size) {
        	 throw new AssertionError("Matrix is not a sparse matrix!!");
         }
         return size;
	}
    /**
   	 * Desc= Constructor for the class: 
   	 * 
   	 * @param array
   	 * @param rows
   	 * @param column
   	 * @throws AssertionError
   	 */
    public SparseMatrix(int[][] a,int rows,int column){
    	this.size=calculateSize(a, rows, column);
        if(size==0) {
        	throw new AssertionError("Empty Matrix!!");
        }
        sparseMatrix=new int[size][3];
        int k = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (a[i][j] != 0)
                {
                    sparseMatrix[k][0] = i;
                    sparseMatrix[k][1] = j;
                    sparseMatrix[k][2] = a[i][j];
                    k++;
                    len++;
                }
            }
        }
    }
    /**
   	 * Desc= Helper method to print array: 
   	 */
    public void printMatrix() {
    	
    	//System.out.println(size);
    	 for (int i = 0; i < this.size; i++)
         {
             for (int j = 0; j < 3; j++)
             {
                 System.out.printf("%d ", sparseMatrix[i][j]);
             }
             System.out.printf("\n");
         }
     }
 

    /**
   	 * Desc= Method to transpose the given array: 
   	 * @return int[][] transposed array
   	 * @throws AssertionError
   	 */
    public int[][] transpose() {
		int tanspose[][]=new int[size][3];
		 // same number of elements
 
        // to count number of elements in each column
        int count[] = new int[size+1];
 
        // initialize all to 0
        for (int i = 1; i <= size; i++)
            count[i] = 0;
 
        for (int i = 0; i <size; i++)
            count[sparseMatrix[i][1]]++;
 
        int[] index = new int[size+1];
 
        // to count number of elements having col smaller
        // than particular i
 
        
        index[0] = 0;
 
        // initialize rest of the indices
        for (int i = 1; i <=size; i++)
 
            index[i] = index[i - 1] + count[i - 1];
 

        
        for (int i = 0; i < size; i++) {
 
           int location=index[sparseMatrix[i][1]]++;
           // transpose row=col
           tanspose[location][0] = sparseMatrix[i][1];

           // transpose col=row
           tanspose[location][1] = sparseMatrix[i][0];

           // same value
           tanspose[location][2] = sparseMatrix[i][2];
          // index[colNo]++;
        }
 
       
        return tanspose;
	}
    
    
    /**
   	 * Desc= Method to add the given arrays:
   	 * @param object 
   	 * @return int[][] added array
   	 * @throws AssertionError
   	 */
    public int[][] addMatrices(SparseMatrix obj) throws AssertionError
    {
        if(row!=obj.row || col!=obj.col)
            throw new AssertionError("For Addition order of given matrices must be same");
        
        List<int[]>temp=new ArrayList<int[]>();
        int apos=0,bpos=0;
        while(apos<sparseMatrix.length && bpos<obj.sparseMatrix.length) {
            if(sparseMatrix[apos][0]==obj.sparseMatrix[bpos][0]) {
                if(sparseMatrix[apos][1]==obj.sparseMatrix[bpos][1]) {
                    int rowMatrix[]=new int[3];
                    rowMatrix[0]=sparseMatrix[apos][0];
                    rowMatrix[1]=sparseMatrix[apos][1];
                    rowMatrix[2]=sparseMatrix[apos][2]+obj.sparseMatrix[bpos][2];
                    temp.add(rowMatrix);
                    apos++;
                    bpos++;
                }
                else if(sparseMatrix[apos][1]<obj.sparseMatrix[bpos][1]) {
                    int rowMatrix[]=new int[3];
                    rowMatrix[0]=sparseMatrix[apos][0];
                    rowMatrix[1]=sparseMatrix[apos][1];
                    rowMatrix[2]=sparseMatrix[apos][2];
                    temp.add(rowMatrix);
                    apos++;
                }
                else {
                    int rowMatrix[]=new int[3];
                    rowMatrix[0]=obj.sparseMatrix[bpos][0];
                    rowMatrix[1]=obj.sparseMatrix[bpos][1];
                    rowMatrix[2]=obj.sparseMatrix[bpos][2];
                    temp.add(rowMatrix);
                    bpos++;
                }
            }
            else {
                 if(sparseMatrix[apos][0]<obj.sparseMatrix[bpos][0]) {
                    int rowMatrix[]=new int[3];
                    rowMatrix[0]=sparseMatrix[apos][0];
                    rowMatrix[1]=sparseMatrix[apos][1];
                    rowMatrix[2]=sparseMatrix[apos][2];
                    temp.add(rowMatrix);
                    apos++;
                }
                else {
                    int rowMatrix[]=new int[3];
                    rowMatrix[0]=obj.sparseMatrix[bpos][0];
                    rowMatrix[1]=obj.sparseMatrix[bpos][1];
                    rowMatrix[2]=obj.sparseMatrix[bpos][2];
                    temp.add(rowMatrix);
                    bpos++;
                }
            }
        }
        
        while(apos < sparseMatrix.length)
        {
            int rowMatrix[]=new int[3];
            rowMatrix[0]=sparseMatrix[apos][0];
            rowMatrix[1]=sparseMatrix[apos][1];
            rowMatrix[2]=sparseMatrix[apos][2];
            temp.add(rowMatrix);
            apos++;
        }
        
        while(bpos < obj.sparseMatrix.length)
        {
            int rowMatrix[]=new int[3];
            rowMatrix[0]=obj.sparseMatrix[bpos][0];
            rowMatrix[1]=obj.sparseMatrix[bpos][1];
            rowMatrix[2]=obj.sparseMatrix[bpos][2];
            temp.add(rowMatrix);
            bpos++;
        }
        int result[][]=new int[temp.size()][3];
        for(int i=0;i<temp.size();i++)
        {
            result[i]=temp.get(i);
            
        }
        return result;
    }
    /**
   	 * Desc= Method to check the weather the array is symmetrical or not:
   	 * @param object
   	 * @return boolean
   	 */
    public boolean isSymmetrical(SparseMatrix a) {
		int[][] symmetricMatrix=a.transpose();
		for(int i=0;i<size;i++) {
			if(sparseMatrix[i][2]!=symmetricMatrix[i][2]) {
				return false;
			}
		}
		return true;
	}
    /**
   	 * Desc= Method to Multiply the given arrays:
   	 * @param object 
   	 * @return int[][] Multiplied array
   	 * @throws AssertionError
   	 */
    public int[][] multiplyMatrices(SparseMatrix obj)
	{
		if(row!=obj.col || col!=obj.row)
			throw new AssertionError("row of first matrix and column of second should be equal to multiply two matrix");
		
		int secondMatrix[][]=obj.transpose();
		
		int[][] outputMatrix = new int[row][obj.col];
		
		for(int indexOuter = 0 ; indexOuter < size ; indexOuter ++)
		{
			for(int indexInner = 0 ; indexInner < obj.size ; indexInner ++)
			{
				if(sparseMatrix[indexOuter][1] == secondMatrix[indexInner][1])
				outputMatrix[sparseMatrix[indexOuter][0]][ secondMatrix[indexInner][0]] +=  (sparseMatrix[indexOuter][2] * secondMatrix[indexInner][2]);
			}
		}
		//printMatrix(new SparseMatricesOperations(outputMatrix , row , col).sparseMatrix);
		return new SparseMatrix(outputMatrix , row , col).sparseMatrix;
	}
}