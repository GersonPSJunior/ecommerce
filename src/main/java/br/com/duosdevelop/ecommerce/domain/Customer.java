package br.com.duosdevelop.ecommerce.domain;

import br.com.duosdevelop.ecommerce.domain.enums.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String document;
	private Integer type;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "tel")
	private Set<String> tel = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Customer() {}
	public Customer(Long id, String name, String email, String document, TypeCustomer type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.document = document;
		this.type = (type == null) ? null : type.getCod();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public TypeCustomer getType() {
		return TypeCustomer.toEnum(type);
	}
	public void setType(TypeCustomer type) {
		this.type = type.getCod();
	}
	public Set<String> getTel() {
		return tel;
	}
	public void setTel(Set<String> tel) {
		this.tel = tel;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
