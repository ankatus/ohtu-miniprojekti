package filters;

import domain.Lukuvinkki;
import filters.Filter;

public class NotLuettu implements Filter {

    @Override
    public boolean matches(Lukuvinkki l) {
        return !l.getLuettu();
    }
}
