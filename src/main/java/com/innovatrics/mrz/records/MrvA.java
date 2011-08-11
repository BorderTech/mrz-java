/**
 * Java parser for the MRZ records, as specified by the ICAO organization.
 * Copyright (C) 2011 Innovatrics s.r.o.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.innovatrics.mrz.records;

import com.innovatrics.mrz.MrzParser;
import com.innovatrics.mrz.MrzRange;
import com.innovatrics.mrz.MrzRecord;
import com.innovatrics.mrz.types.MrzFormat;

/**
 * MRV type-A format: A two lines long, 44 characters per line format
 * @author Jeremy Le Berre
 */
public class MrvA extends MrzRecord {

    private static final long serialVersionUID = 1L;

    public MrvA() {
        super(MrzFormat.MRV_VISA_A);
    }
    /**
     * Optional data at the discretion of the issuing State
     */
    public String optional;

    @Override
    public void fromMrz(String mrz) {
        super.fromMrz(mrz);
        final MrzParser parser = new MrzParser(mrz);
        setName(parser.parseName(new MrzRange(5, 44, 0)));
        documentNumber = parser.parseString(new MrzRange(0, 9, 1));
        parser.checkDigit(9, 1, new MrzRange(0, 9, 1), "passport number");
        nationality = parser.parseString(new MrzRange(10, 13, 1));
        dateOfBirth = parser.parseDate(new MrzRange(13, 19, 1));
        parser.checkDigit(19, 1, new MrzRange(13, 19, 1), "date of birth");
        sex = parser.parseSex(20, 1);
        expirationDate = parser.parseDate(new MrzRange(21, 27, 1));
        parser.checkDigit(27, 1, new MrzRange(21, 27, 1), "expiration date");
        optional = parser.parseString(new MrzRange(28, 44, 1));
    }

    @Override
    public String toString() {
        return "MRV-A{" + super.toString() + ", optional=" + optional + '}';
    }

    @Override
    public String toMrz() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}