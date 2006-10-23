/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction;

/**
 * Exception thrown when the existence or non-existence of a transaction
 * amounts to an illegal state according to the transaction propagation
 * behavior that applies.
 *
 * @author Juergen Hoeller
 * @since 21.01.2004
 */
public class IllegalTransactionStateException extends TransactionUsageException {

	/**
	 * Constructor for IllegalTransactionStateException.
	 * @param msg message
	 */
	public IllegalTransactionStateException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for IllegalTransactionStateException.
	 * @param msg message
	 * @param ex root cause from transaction API in use
	 */
	public IllegalTransactionStateException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
