// Copyright (c) ZNbase, Inc.

package com.ZNbase.yw.controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(TokenAuthenticator.class)
public abstract class AuthenticatedController extends Controller {}
