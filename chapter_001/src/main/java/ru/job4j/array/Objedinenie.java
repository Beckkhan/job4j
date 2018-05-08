package ru.job4j.array;

class Objedinenie {

	public int[] objedinyaem(int[] a, int[] b) {

		int[] c = new int[a.length + b.length];
		int i = 0, j = 0;
		for (int k = 0; k < c.length; k++) {

			if (i > a.length - 1) {
				c[k] = b[j];
				j++;
			} else if (j > b.length - 1) {
				c[k] = a[i];
				i++;
			} else if (a[i] < b[j]) {
				c[k] = a[i];
				i++;
			} else {
				c[k] = b[j];
				j++;
			}
		}
		return c;
	}
}