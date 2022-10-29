package immutable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SparseMatrixTest {


	@Test
	void test1() {
		SparseMatrix SparseMatrix=new SparseMatrix(new int[][]{{0 , 0 , 1 },
	        {0 , 0 , 0  },
	        {1 , 0 , 0 }},3,3);
		boolean ok=true;
		 assertEquals(ok, SparseMatrix.isSymmetrical(SparseMatrix));
	}
	@Test
	void test2() {
		int[][] arr={{0 , 0 , 0 },
		        {0, 0 , 0  },
		        {0 , 0 , 0 }};
        var error = Assertions.assertThrows(AssertionError.class, () -> {
        	//SparseMatrix.calculateSize(arr, 3, 3);
        	@SuppressWarnings("unused")
			SparseMatrix SparseMatrix=new SparseMatrix(arr,3,3);
        });
        assertEquals("Empty Matrix!!", error.getMessage());
	}
	@Test
	void test3() {
		SparseMatrix SparseMatrix=new SparseMatrix(new int[][]{{0 , 2 , 0 },
	        {0 , 4, 0  },
	        {0, 0 , 9 }},3,3);
		int [][] result= {{1,0,2},{1,1,4},{2,2,9}};
		 assertArrayEquals(result, SparseMatrix.transpose());
	
	}
	@Test
	void test4() {
		SparseMatrix SparseMatrix=new SparseMatrix(new int[][]{{2 , 0 },
	        {0 , 4 },
	        {0, 0  }},3,2);
		int [][] result= {{0,0,2},{1,1,4}};
		 assertArrayEquals(result, SparseMatrix.transpose());
	
	}
	@Test
	void test5() {
		SparseMatrix SparseMatrix=new SparseMatrix();	
        var error = Assertions.assertThrows(AssertionError.class, () -> {
        	SparseMatrix.calculateSize(new int[][]{{1 , 0 , 0 },
		        {3, 0 , 2  },
		        {0 , 6 , 4 }}, 3, 3);     	
        });
        assertEquals("Matrix is not a sparse matrix!!", error.getMessage());
	}
	@Test
	void test6() {
		SparseMatrix a=new SparseMatrix(new int[][]{{2 , 0 },
	        {0 , 4 },
	        {0, 0  }},3,2);	
		SparseMatrix b=new SparseMatrix(new int[][]{{0 , 0,1 },
	        {0 , 4,0 }},2,3);
        var error = Assertions.assertThrows(AssertionError.class, () -> {
        	a.addMatrices(b);     	
        });
        assertEquals("For Addition order of given matrices must be same", error.getMessage());
	}
	@Test
	void test7() {
		SparseMatrix a=new SparseMatrix(new int[][]{{2 , 0 },
	        {0 , 4 },
	        {0, 0  }},3,2);	
		SparseMatrix b=new SparseMatrix(new int[][]{{0 , 3 },
	        {0 , 0 },
	        {0, 2  }},3,2);	
		int [][] result= {{0,0,2},{0,1,3},{1,1,4},{2,1,2}};
		 assertArrayEquals(result, a.addMatrices(b));
	
	}
	@Test
	void test8() {
		SparseMatrix a=new SparseMatrix(new int[][]{{0 ,1, 0 },
	        {0 ,0, 2 },
	        {1,2, 0}},3,3);	
		SparseMatrix b=new SparseMatrix(new int[][]{{1 ,2, 0 },
	        {0 ,1, 0 },
	        {0, 0,5  }},3,3);	
		int [][] result= {{0,1,1},{1,2,10},{2,0,1},{2,1,4}};
		 assertArrayEquals(result, a.multiplyMatrices(b));
	
	}
	@Test
	void test9() {
		SparseMatrix a=new SparseMatrix(new int[][]{{2 ,0 ,0},
	        {0 , 4 ,0}},2,3);	
		SparseMatrix b=new SparseMatrix(new int[][]{{0 , 0,1 },
	        {0 , 4,0 }},2,3);
        var error = Assertions.assertThrows(AssertionError.class, () -> {
        	a.multiplyMatrices(b) ;    	
        });
        assertEquals("row of first matrix and column of second should be equal to multiply two matrix", error.getMessage());
	}

}
