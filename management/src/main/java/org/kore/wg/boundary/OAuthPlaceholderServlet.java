/*
 * Copyright (C) 2020 Konrad Renner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kore.wg.boundary;

import java.io.IOException;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Konrad Renner
 */
@WebServlet(urlPatterns = {"/servlet/*"})
public class OAuthPlaceholderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path.equals("/make-external-call")) {
            // Fake making an external call without involving the UI
            // e.g. OAuth Authentication Flow will have a few of these, resulting in
            // receiving the token eventually
            resp.sendRedirect("/servlet/callback");
        } else if (path.equals("/callback")) {
            // Redirect back to a path controlled by the Angular client
            String redirectPath = "/clientCallback";
            boolean proxy = Boolean.getBoolean("ui.proxy");
            if (proxy && checkProxyIsRunning()) {
                redirectPath = "http://localhost:4200" + redirectPath;
            }
            resp.sendRedirect(redirectPath);
        } else {
            resp.sendError(404);
        }
    }

    private boolean checkProxyIsRunning() {
        try (Socket s = new Socket("localhost", 4200)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
