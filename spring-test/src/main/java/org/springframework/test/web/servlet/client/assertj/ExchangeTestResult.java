/*
 * Copyright 2002-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.web.servlet.client.assertj;

import org.assertj.core.api.AssertProvider;

import org.springframework.test.web.servlet.client.ExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

/**
 * {@link AssertProvider} for {@link RestTestClient} that holds the result of a
 * performed request, and is intended for further use with AssertJ. For example:
 *
 * <pre class="code">
 * ResponseSpec spec = restTestClient.get().uri("/greeting").exchange();
 *
 * ExchangeTestResult result = ExchangeTestResult.of(spec);
 * assertThat(result).hasStatusOk();
 * assertThat(result).contentType().isCompatibleWith(MediaType.APPLICATION_JSON);
 * assertThat(result).bodyJson().extractingPath("$.message").asString().isEqualTo("Hello World");
 * </pre>
 *
 * @author Rossen Stoyanchev
 * @since 7.0
 */
public interface ExchangeTestResult extends AssertProvider<ExchangeTestResultAssert> {

	/**
	 * Return the underlying {@link ExchangeResult}.
	 */
	ExchangeResult getExchangeResult();


	/**
	 * Create an instance from a {@link RestTestClient.ResponseSpec}.
	 */
	static ExchangeTestResult of(RestTestClient.ResponseSpec responseSpec) {
		return of(responseSpec.returnResult(byte[].class));
	}

	/**
	 * Create an instance from an {@link ExchangeResult}.
	 */
	static ExchangeTestResult of(ExchangeResult exchangeResult) {
		return new DefaultExchangeTestResult(exchangeResult);
	}

}
