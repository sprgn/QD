/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2019 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.devexperts.qd.impl.matrix.management.dump;

import java.io.PrintStream;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;

public class DumpUtil {
	static void printTime(PrintStream out, DataRecord r, RecordCursor cursor) {
		for (int i = 0; i < 2; i++) {
			out.print('\t');
			out.print(r.getIntField(i).toString(cursor.getInt(i)));
		}
	}

	static String timeString(DataRecord r, long time) {
		return time + " {" +
			r.getIntField(0).toString((int)(time >> 32)) + " " +
			r.getIntField(1).toString((int)time) + "}";
	}
}
