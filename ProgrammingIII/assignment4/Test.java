package assignment4;

public class Test {
	public static void main(String[] args){
		//Array of Rationals for test
		Rational[] test = {new Rational(3,2), new Rational(7,4), new Rational(1,3), new Rational(20,7), new Rational(-1,5),
				new Rational(15,8), new Rational(-33,28), new Rational(6,5), new Rational(200, 27), new Rational(-1,9)};
		//Create new Rational Collection
		RationalCollection rc = new RationalCollection(test);
		//Show Pre-Sorted Array List
		System.out.println("Array List of Rationals Before Sorting: ");
		System.out.println(rc.toString());
		//Sort
		rc.sort();
		//Show Sorted Array List
		System.out.println("Array List of Rationals After Sorting: ");
		System.out.println(rc.toString());
	}
}
