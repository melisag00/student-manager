package Lol.Customer;

import java.util.Objects;

public class customer {
    private String username;
    private String password;
    private String role;
    private String rank;
    private String customer_role;
    private String partner_role;

    public customer() {
    }

    public customer(String username, String customer_role, String rank, String partner_role) {
        this.username = username;
        this.customer_role = customer_role;
        this.rank = rank;
        this.partner_role = partner_role;
    }

    public customer(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCustomer_role() {
        return customer_role;
    }

    public void setCustomer_role(String customer_role) {
        this.customer_role = customer_role;
    }

    public String getPartner_role() {
        return partner_role;
    }

    public void setPartner_role(String partner_role) {
        this.partner_role = partner_role;
    }

    @Override
    public String toString() {
        return "customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", rank='" + rank + '\'' +
                ", customer_role='" + customer_role + '\'' +
                ", partner_role='" + partner_role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof customer)) return false;
        customer customer = (customer) o;
        return Objects.equals(username, customer.username) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(role, customer.role) &&
                Objects.equals(rank, customer.rank) &&
                Objects.equals(customer_role, customer.customer_role) &&
                Objects.equals(partner_role, customer.partner_role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, rank, customer_role, partner_role);
    }
}
