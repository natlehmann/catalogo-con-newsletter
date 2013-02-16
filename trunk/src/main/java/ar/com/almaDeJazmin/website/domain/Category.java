package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 4398671996997384880L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=255, nullable=false, unique=true)
	private String name;
	
	@ManyToMany(mappedBy="categories", fetch=FetchType.LAZY)
	private List<Product> products;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Category " + this.name + " (id: " + this.id + ")";
	}

	public static String getSortByField(Locale locale) {
		return "name";
	}

}
