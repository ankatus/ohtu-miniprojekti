package filters;

import domain.Lukuvinkki;

public interface Filter {
    public boolean matches(Lukuvinkki l);
}
