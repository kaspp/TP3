import java.util.ArrayList;

public class DoAnalyse {
	ConnectToDB db;
	ArrayList<String> food = new ArrayList<String>();
	ArrayList<String> country = new ArrayList<String>();
	ArrayList<String> neg = new ArrayList<String>();
	ArrayList<String> pos = new ArrayList<String>();
	ArrayList<String> restr = new ArrayList<String>();
	ArrayList<String> ct = new ArrayList<String>();
	ArrayList<ArrayList<String>> collate = new ArrayList<ArrayList<String>>();
	ArrayList<String> chk = new ArrayList<String>();
	ArrayList<Integer> check;

	public DoAnalyse() {
		db = new ConnectToDB();
		init();

	}

	public void clear() {
		chk.clear();
		check.clear();
	}

	public ArrayList<String> doCheck() {
		for (int temp : check) {
			switch (temp) {
			case 1:
				chk.add("Food category found");

				break;

			case 2:
				chk.add("Country found");
				break;

			case 3:
				chk.add("Cooking terms found");
				break;

			case 4:
				chk.add("Negative Sentiments found");
				break;

			case 5:
				chk.add("Positive Sentiments found");
				break;

			case 6:
				chk.add("Restaurant found");
				break;

			default:
				// do nothing.
				break;

			}
		}

		return chk;
	}

	public ArrayList<String> checkPhrase(String phrase) {

		String[] slit = phrase.split(" ");
		check = new ArrayList<Integer>();

		// check multiple. 
		// added new line here only.
		for (int i = 0; i < slit.length; i++) {
			for (int j = 0; j < slit.length; j++) {
				if (i == j) {
					String word = slit[i];
					switch (checkWord(word)) {
					case 1:
						check.add(1);

						break;

					case 2:
						check.add(2);
						break;

					case 3:
						check.add(3);
						break;

					case 4:
						check.add(4);
						break;

					case 5:
						check.add(5);
						break;

					case 6:
						check.add(6);
						break;

					default:
						// do nothing.
						break;
					}
				} else {
					String word = slit[i] + " " + slit[j];
					switch (checkWord(word)) {
					case 1:
						check.add(1);

						break;

					case 2:
						check.add(2);
						break;

					case 3:
						check.add(3);
						break;

					case 4:
						check.add(4);
						break;

					case 5:
						check.add(5);
						break;

					case 6:
						check.add(6);
						break;

					default:
						// do nothing.
						break;
					}
				}
			}
		}

		if (check.contains(1)) {
			chk.add("The statement is a food post!");
		} else
			chk.add("The statement is not a food post!");

		return doCheck();
	}

	public int checkWord(String word) {
		boolean ifcontained = false;
		int count = 0;
		for (ArrayList<String> p : collate) {
			count++;
			if (p.contains(word.toUpperCase())) {
				ifcontained = true;
				break;
			}
		}

		if (ifcontained) {
			switch (count) {
			case 1:
				return 1;

			case 2:
				return 2;

			case 3:
				return 3;

			case 4:
				return 4;

			case 5:
				return 5;

			case 6:
				return 6;
			}

		} else {
			System.out.println("The word is not found!");
			return 0;
		}
		return 0;
	}

	public void init() {

		food = db.retriDict("food.txt");
		country = db.retriDict("country.txt");
		ct = db.retriDict("cookingterms.txt");
		neg = db.retriDict("negativewords.txt");
		pos = db.retriDict("positivewords.txt");
		restr = db.retriDict("restr.txt");

		collate.add(food);
		collate.add(country);
		collate.add(ct);
		collate.add(neg);
		collate.add(pos);
		collate.add(restr);

	}

}
