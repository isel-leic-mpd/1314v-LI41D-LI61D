/*
 * Copyright (C) 2014 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package isel.mpd.raffle;

import static java.lang.Integer.parseInt;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Student {
    private final int nr;
    private final String name;
    private final int grade;
    private final List<Date> deliverables = new LinkedList<>();
    
    public Student(int nr, String name, int grade) {
        this.nr = nr;
        this.name = name;
        this.grade = grade;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deliverables == null) ? 0 : deliverables.hashCode());
		result = prime * result + grade;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + nr;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (deliverables == null) {
			if (other.deliverables != null)
				return false;
		} else if (!deliverables.equals(other.deliverables))
			return false;
		if (grade != other.grade)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nr != other.nr)
			return false;
		return true;
	}

	public Student(String line){
        String [] tokens = line.split("\\|");
        if(tokens.length < 3) throw new IllegalArgumentException("Invalid line parameter");
        this.nr = parseInt(tokens[1]);
        this.name = tokens[3];
        this.grade = tokens.length == 5? parseInt(tokens[4]): 10;
    }    

    @Override
    public String toString() {
        return "Student{" + "nr=" + nr + ", name=" + getName() + ", grade=" + getGrade() + '}';
    }

    void print() {
        System.out.println(this);
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @return the nr
	 */
	public int getNumber() {
		return nr;
	}
       
}
